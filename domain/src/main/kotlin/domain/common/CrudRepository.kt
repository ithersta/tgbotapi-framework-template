package domain.common

interface CrudRepository<Entity : Any, NewEntity : Any, Id : Any> {
    suspend fun add(entity: NewEntity): Entity
    suspend fun get(id: Id): Entity
    suspend fun getAll(): List<Entity>
    suspend fun update(entity: Entity)
    suspend fun delete(id: Id)
}
