package com.entra21.gfarm.service;

import com.entra21.gfarm.dto.CultivoDTO;
import com.entra21.gfarm.model.Cultivo;
import com.entra21.gfarm.repository.CultivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CultivoService {

    @Autowired
    private CultivoRepository cultivoRepository;

    public Set<CultivoDTO> listarCultivosDTO() {
        Set<Cultivo> cultivos = new HashSet<>(cultivoRepository.findAll());
        return cultivos.stream().map(CultivoDTO::fromEntity).collect(Collectors.toSet());
    }

    public CultivoDTO obterCultivoDTOPorId(Long id) {
        Cultivo cultivo = cultivoRepository.findById(id).orElse(null);
        return (cultivo != null) ? CultivoDTO.fromEntity(cultivo) : null;
    }

    public CultivoDTO criarCultivoDTO(CultivoDTO cultivoDTO) {
        Cultivo cultivo = new Cultivo();
        cultivo.setNome(cultivoDTO.getNome());
        cultivo.setDataDePlantio(cultivoDTO.getDataDePlantio());
        cultivo.setDataColheitaPrevista(cultivoDTO.getDataColheitaPrevista());
        Cultivo novaCultivo = cultivoRepository.save(cultivo);
        return CultivoDTO.fromEntity(novaCultivo);
    }

}
