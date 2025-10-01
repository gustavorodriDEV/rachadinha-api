package com.gustavo.rachadinha_api.services;

import com.gustavo.rachadinha_api.dtos.DespesaDTO;
import com.gustavo.rachadinha_api.entities.Despesa;
import com.gustavo.rachadinha_api.entities.Grupo;
import com.gustavo.rachadinha_api.entities.Participante;
import com.gustavo.rachadinha_api.services.exceptions.RegraDeNegocioException;
import com.gustavo.rachadinha_api.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
