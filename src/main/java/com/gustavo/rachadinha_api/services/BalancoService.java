package com.gustavo.rachadinha_api.services;

import com.gustavo.rachadinha_api.dtos.BalancoDTO;
import com.gustavo.rachadinha_api.entities.Despesa;
import com.gustavo.rachadinha_api.entities.Participante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class BalancoService {

    private final DespesaService despesaService;
    private final ParticipanteService participanteService;

    @Autowired
    public BalancoService(DespesaService despesaService, ParticipanteService participanteService) {
        this.despesaService = despesaService;
        this.participanteService = participanteService;
    }

    public List<BalancoDTO> calcularBalanco(Long idGrupo) {
        List<Despesa> despesas = despesaService.listarDespesasPorGrupo(idGrupo);
        List<Participante> participantes = participanteService.listarParticipantesPorGrupo(idGrupo);

        if (despesas.isEmpty() || participantes.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Long, BigDecimal> saldoParticipante = new HashMap<>();

        for (Participante participante : participantes) {
            saldoParticipante.put(participante.getId(), BigDecimal.ZERO);
        }

        BigDecimal numeroParticipantes = new BigDecimal(participantes.size());

        for (Despesa despesa : despesas) {
            BigDecimal valorTotal = despesa.getValor();
            BigDecimal valorPorPessoa = valorTotal.divide(numeroParticipantes, 2, RoundingMode.HALF_UP);

            // Quem pagou RECEBE crédito (+)
            Long idPagador = despesa.getPagador().getId();
            BigDecimal saldoAtualPagador = saldoParticipante.get(idPagador);
            saldoParticipante.put(idPagador, saldoAtualPagador.add(valorTotal));

            for (Participante p : participantes) {
                BigDecimal saldoP = saldoParticipante.get(p.getId());
                saldoParticipante.put(p.getId(), saldoP.subtract(valorPorPessoa));
            }
        }

        // Separar Devedores e Credores
        List<ParticipanteSaldo> devedores = new ArrayList<>();
        List<ParticipanteSaldo> credores = new ArrayList<>();

        for (Participante p : participantes) {
            BigDecimal saldo = saldoParticipante.get(p.getId());
            if (saldo.compareTo(BigDecimal.ZERO) < 0) {
                devedores.add(new ParticipanteSaldo(p.getNome(), saldo));
            } else if (saldo.compareTo(BigDecimal.ZERO) > 0) {
                credores.add(new ParticipanteSaldo(p.getNome(), saldo));
            }
        }
       // 4. Gerar transações (Algoritmo Guloso Simplificado)
        List<BalancoDTO> pagamentos = new ArrayList<>();

        int iDevedor = 0;
        int iCredor = 0;

        while (iDevedor < devedores.size() && iCredor < credores.size()) {
            ParticipanteSaldo devedor = devedores.get(iDevedor);
            ParticipanteSaldo credor = credores.get(iCredor);

            // O valor a ser pago é o mínimo entre o que um deve e o que o outro tem a receber
            BigDecimal valorDebito = devedor.saldo.abs(); // Transforma negativo em positivo
            BigDecimal valorCredito = credor.saldo;

            BigDecimal valorPagamento = valorDebito.min(valorCredito);

            pagamentos.add(new BalancoDTO(devedor.nome, credor.nome, valorPagamento));

            // Atualiza os saldos temporários
            devedor.saldo = devedor.saldo.add(valorPagamento);
            credor.saldo = credor.saldo.subtract(valorPagamento);

            // Avança os ponteiros se a dívida/crédito foi quitada
            if (devedor.saldo.compareTo(BigDecimal.ZERO) == 0) iDevedor++;
            if (credor.saldo.compareTo(BigDecimal.ZERO) == 0) iCredor++;
        }

        return pagamentos;
    }

    // Classe auxiliar interna para facilitar o cálculo
    private static class ParticipanteSaldo {
        String nome;
        BigDecimal saldo;

        public ParticipanteSaldo(String nome, BigDecimal saldo) {
            this.nome = nome;
            this.saldo = saldo;
        }
    }
}
