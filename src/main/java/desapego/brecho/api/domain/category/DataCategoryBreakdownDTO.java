package desapego.brecho.api.domain.category;

public record DataCategoryBreakdownDTO(
    Long id,
    String descCategoria,
    Boolean status
) {
    public DataCategoryBreakdownDTO(Category category) {
        this(
            category.getId(),
            category.getDescCategoria(),
            category.getStatus()
        );
    }
}
