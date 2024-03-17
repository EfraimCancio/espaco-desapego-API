package desapego.brecho.api.domain.size;

public record DataSizeBreakdownDTO(
     Long id,
     String descTamanho,
     Boolean status
) {
    public DataSizeBreakdownDTO(Size size) {
        this(
                size.getId(),
                size.getDescTamanho(),
                size.getStatus()
        );
    }
}
