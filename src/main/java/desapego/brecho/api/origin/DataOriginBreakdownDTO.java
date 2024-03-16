package desapego.brecho.api.origin;

import jakarta.persistence.*;

public record DataOriginBreakdownDTO(
      Long id,
      String descOrigem,
      Type tipoOrigem,
      Boolean status
    ) {
      public DataOriginBreakdownDTO(Origin origin) {
          this(
                  origin.getId(),
                  origin.getDescOrigem(),
                  origin.getTipoOrigem(),
                  origin.getStatus()
          );
      }
}
