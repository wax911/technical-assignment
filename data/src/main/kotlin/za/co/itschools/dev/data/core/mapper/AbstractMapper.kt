package za.co.itschools.dev.data.core.mapper

/**
 * A simple mapper for converting [Model] to [Entity]
 */
internal abstract class AbstractMapper<Model, Entity> {
    protected abstract val fromType: (source: Model) -> Entity

    /**
     * Convert [model] to an entity type
     */
    fun from(model: Model): Entity =
        fromType(model)
}