package com.servicios;

import com.modelo.Afiliado;
import com.repositorio.AfiliadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AfiliadoService {

    private final AfiliadoRepository afiliadoRepository;

    public AfiliadoService(AfiliadoRepository afiliadoRepository) {
        this.afiliadoRepository = afiliadoRepository;
    }

    // Guardar afiliado
    public Afiliado guardarAfiliado(Afiliado afiliado) {
        return afiliadoRepository.save(afiliado);
    }

    // Obtener todos los afiliados
    public List<Afiliado> obtenerTodos() {
        return afiliadoRepository.findAll();
    }

    // Obtener un afiliado por ID
    public Afiliado obtenerPorId(Long id) {
        return afiliadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Afiliado no encontrado con ID: " + id));
    }

    // Actualizar un afiliado existente
    public Afiliado actualizarAfiliado(Long id, Afiliado afiliadoActualizado) {
        Afiliado afiliadoExistente = afiliadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Afiliado no encontrado con ID: " + id));

        afiliadoExistente.setNombre(afiliadoActualizado.getNombre());
        afiliadoExistente.setTipoIdentificacion(afiliadoActualizado.getTipoIdentificacion());
        afiliadoExistente.setNumeroIdentificacion(afiliadoActualizado.getNumeroIdentificacion());
        afiliadoExistente.setFechaNacimiento(afiliadoActualizado.getFechaNacimiento());
        afiliadoExistente.setDireccionResidencia(afiliadoActualizado.getDireccionResidencia());
        afiliadoExistente.setTelefono(afiliadoActualizado.getTelefono());
        afiliadoExistente.setEmpresaAfiliado(afiliadoActualizado.getEmpresaAfiliado());
        afiliadoExistente.setTipoParentesco(afiliadoActualizado.getTipoParentesco());
        afiliadoExistente.setIdContribuyente(afiliadoActualizado.getIdContribuyente());

        return afiliadoRepository.save(afiliadoExistente);
    }

    // Eliminar un afiliado por ID
    public void eliminarAfiliado(Long id) {
        if (!afiliadoRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar, afiliado no encontrado con ID: " + id);
        }
        afiliadoRepository.deleteById(id);
    }
}
