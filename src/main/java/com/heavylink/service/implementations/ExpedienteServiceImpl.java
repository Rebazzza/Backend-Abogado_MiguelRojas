package com.heavylink.service.implementations;

import com.heavylink.model.Expediente;
import com.heavylink.model.Notificacion;
import com.heavylink.repository.IExpediente;
import com.heavylink.service.IExpedienteService;
import com.heavylink.service.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpedienteServiceImpl implements IExpedienteService {

    @Autowired
    private IExpediente repoExpediente;

    // Aquí "llamamos" al otro servicio para poder crear notificaciones
    @Autowired
    private INotificacionService servicioNotificacion;

    @Transactional // Si falla la notificación, no se guarda el expediente
    @Override
    public Expediente registrar(Expediente expediente) throws Exception {
        // 1. Guardamos el expediente en la base de datos
        Expediente nuevoExpediente = repoExpediente.save(expediente);

        // 2. Lógica Automática: Crear notificación para el especialista
        if (nuevoExpediente.getEspecialista() != null) {
            Notificacion aviso = new Notificacion();
            aviso.setTitulo("Nuevo Expediente Asignado");
            aviso.setMensaje("Se le ha asignado el expediente: " + nuevoExpediente.getTitulo());
            aviso.setFecha(LocalDateTime.now()); // Asegúrate de usar el nombre correcto de tu variable
            aviso.setLeido(false);

            // AQUÍ ESTÁ LA CLAVE: Asignamos el mismo especialista que tiene el expediente
            aviso.setEspecialista(nuevoExpediente.getEspecialista());
            servicioNotificacion.registrar(aviso);
        }

        return nuevoExpediente;
    }

    @Override
    public Expediente modificar(Expediente expediente) throws Exception {
        return repoExpediente.save(expediente);
    }

    @Override
    public List<Expediente> listar() throws Exception {
        return repoExpediente.findAll();
    }

    @Override
    public Expediente leerPorId(Integer id) throws Exception {
        return repoExpediente.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        repoExpediente.deleteById(id);
    }
}