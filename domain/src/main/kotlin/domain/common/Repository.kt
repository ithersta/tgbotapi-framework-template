package domain.common

interface Repository<Entity : Any, NewEntity : Any, Id : Any> {
    fun add(entity: NewEntity): Entity
    fun get(id: Id): Entity
    fun getAll(): List<Entity>
    fun delete(id: Id)
}
