package com.aconcaguasf.basa.digitalize.bussiness;


import com.aconcaguasf.basa.digitalize.dto.ElementosOperacionDTO;
import com.aconcaguasf.basa.digitalize.model.Operaciones;
import com.aconcaguasf.basa.digitalize.model.TipoOperaciones;
import com.aconcaguasf.basa.digitalize.repository.ElementosRepository;
import com.aconcaguasf.basa.digitalize.repository.OperacionesRepository;
import com.aconcaguasf.basa.digitalize.repository.RequerimientoRepository;
import com.aconcaguasf.basa.digitalize.repository.TipoOperacionesRepository;
import com.aconcaguasf.basa.digitalize.service.ElementosOperacionProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("operacionBussiness")
public class OperacionBussiness {

    @Autowired
    ElementosRepository elementosRepository;
    @Autowired
    OperacionesRepository operacionesRepository;
    @Autowired
    RequerimientoRepository requerimientoRepository;
    @Autowired
    TipoOperacionesRepository tipoOperacionesRepository;
    
    public boolean checkElementClient(String codigo, Long idCliEle){
        return (elementosRepository.findByCodigoAndClienteEmp_id(codigo, idCliEle)!=null);
    }

  /*  public boolean checkReqElement(String codigo) {
        return elementosRepository.findByReqElement(codigo) != null;
    }*/
  /*  public ElementosOperacionDTO checkReqElement(String codigo) {
        return elementosRepository.findByReqElement(codigo);
    } */
  public ElementosOperacionDTO checkReqElement(String codigo) {
      List<Object[]> results = elementosRepository.findByReqElement(codigo);

      if (!results.isEmpty()) {
          Object[] result = results.get(0);
          String codigoElement = (String) result[0];
          Long operacionId = ((Number) result[1]).longValue(); // Asegúrate de que el tipo de dato sea correcto
          return new ElementosOperacionDTO(codigoElement, operacionId);
      } else {
          return null;
      }
  }


    void nextOperation(Long idReq) {
        Operaciones operaciones = operacionesRepository.findFirstByRequerimiento_id(idReq);
        operaciones.setTipoOperacion_id(operaciones.getTipoOperaciones().getTipoOperacionSiguiente_id().toString());
        operacionesRepository.save(operaciones);
    }

    void backOperationToAsignarHDR(Long pReqId){
        Operaciones operaciones = operacionesRepository.findFirstByRequerimiento_id(pReqId);
        TipoOperaciones tOpCtrElems, tOpAsgHDRs;
        tOpCtrElems = tipoOperacionesRepository.findByPreviousIdCtrlElem(operaciones.getTipoOperaciones().getId());
        tOpAsgHDRs  = (tOpCtrElems != null)
                        ? tipoOperacionesRepository.findByPreviousIdAsgHdR(tOpCtrElems.getId())
                        : tipoOperacionesRepository.findByPreviousIdAsgHdR(operaciones.getTipoOperaciones().getId());
        operaciones.setTipoOperacion_id(tOpAsgHDRs.getId().toString());
        operacionesRepository.save(operaciones);
    }
}
