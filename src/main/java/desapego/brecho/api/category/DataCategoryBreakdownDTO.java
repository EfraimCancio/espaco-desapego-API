package desapego.brecho.api.category;

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
