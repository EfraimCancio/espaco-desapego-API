package desapego.brecho.api.domain.brand;

public record DataBrandBreakdownDTO(
        Long id,

        String descMarca,

        Boolean status
) {
    public DataBrandBreakdownDTO(Brand brand) {
        this(
                brand.getId(),
                brand.getDescMarca(),
                brand.getStatus()
        );
    }
}
