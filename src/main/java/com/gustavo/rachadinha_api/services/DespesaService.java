package com.gustavo.rachadinha_api.services;

import com.gustavo.rachadinha_api.dtos.DespesaDTO;
import com.gustavo.rachadinha_api.entities.Despesa;
import com.gustavo.rachadinha_api.entities.Grupo;
import com.gustavo.rachadinha_api.entities.Participante;
import com.gustavo.rachadinha_api.repositories.DespesaRepository;
import com.gustavo.rachadinha_api.services.exceptions.RecursoNaoEncontradoException;
import com.gustavo.rachadinha_api.services.exceptions.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final GrupoService grupoService;
    private final ParticipanteService participanteService;

    @Autowired
    public DespesaService(DespesaRepository despesaRepository, GrupoService grupoService, ParticipanteService participanteService) {
        this.despesaRepository = despesaRepository;
        this.grupoService = grupoService;
        this.participanteService = participanteService;
    }

    @Transactional
    public Despesa criarDespesa(Long idGrupo, DespesaDTO despesaDTO) {
        Grupo grupo = grupoService.buscarPorId(idGrupo);
        Participante pagador = participanteService.buscarParticipante(despesaDTO.getIdPagador());

        if (!pagador.getGrupo().getId().equals(grupo.getId())) {
            throw new RegraDeNegocioException(String.format(
                    "O pagador '%s' (ID: %d) não pertence ao grupo.",
                    pagador.getNome(),
                    pagador.getId()
            ));
        }

        Despesa novaDespesa = new Despesa();
        novaDespesa.setDescricao(despesaDTO.getDescricao());
        novaDespesa.setValor(despesaDTO.getValor());
        novaDespesa.setData(despesaDTO.getData());
        novaDespesa.setGrupo(grupo);
        novaDespesa.setPagador(pagador);

        return despesaRepository.save(novaDespesa);
    }

    @Transactional(readOnly = true)
    public List<Despesa> listarDespesasPorGrupo(Long idGrupo) {
        grupoService.buscarPorId(idGrupo);
        return despesaRepository.findByGrupoId(idGrupo);
    }

    @Transactional(readOnly = true)
    public Despesa buscarDespesaPorId(Long idDespesa) {
        return despesaRepository.findById(idDespesa)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Despesa não encontrada com o ID: " + idDespesa));
    }

    @Transactional
    public Despesa atualizarDespesa(Long idDespesa, DespesaDTO despesaDTO) {
        Despesa despesaExistente = buscarDespesaPorId(idDespesa);

        if (!despesaExistente.getPagador().getId().equals(despesaDTO.getIdPagador())) {
            Participante novoPagador = participanteService.buscarParticipante(despesaDTO.getIdPagador());

            if (!novoPagador.getGrupo().getId().equals(despesaExistente.getGrupo().getId())) {
                throw new RegraDeNegocioException("O novo pagador não pertence ao grupo desta despesa.");
            }
            despesaExistente.setPagador(novoPagador);
        }

        despesaExistente.setDescricao(despesaDTO.getDescricao());
        despesaExistente.setValor(despesaDTO.getValor());
        despesaExistente.setData(despesaDTO.getData());

        return despesaRepository.save(despesaExistente);
    }

    @Transactional
    public void deletarDespesa(Long idDespesa) {
        if (!despesaRepository.existsById(idDespesa)) {
            throw new RecursoNaoEncontradoException("Despesa não encontrada com o ID: " + idDespesa);
        }
        despesaRepository.deleteById(idDespesa);
    }
}