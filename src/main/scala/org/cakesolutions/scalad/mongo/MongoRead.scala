package org.cakesolutions.scalad.mongo

/** `READ` operations – i.e. requires a key to query the database.
  */
trait MongoRead {
  this: MongoSearch =>

  /** @return the only entity matching the key-based search, or `None`.
    * @throws TooManyResults if more than one result.
    */
  def readUnique[T, K](key: K)(implicit keyBuilder: KeyQueryBuilder[T, K],
                               collectionProvider: CollectionProvider[T],
                               serialiser: MongoSerialiser[T]): Option[T] = {
    val query = keyBuilder.createKeyQuery(key)
    searchUnique(query)
  }

  /** @return the first entity matching the key-based search, or `None`. */
  def readFirst[T, K](key: K)(implicit keyBuilder: KeyQueryBuilder[T, K],
                              collectionProvider: CollectionProvider[T],
                              serialiser: MongoSerialiser[T]): Option[T] = {
    val query = keyBuilder.createKeyQuery(key)
    searchFirst(query)
  }

  /** @return all entities matching the key-based search. */
  def readAll[T, K](key: K)(implicit keyBuilder: KeyQueryBuilder[T, K],
                            collectionProvider: CollectionProvider[T],
                            serialiser: MongoSerialiser[T]): ConsumerIterator[T] = {
    val query = keyBuilder.createKeyQuery(key)
    searchAll(query)
  }
}

// TODO: selective READ
