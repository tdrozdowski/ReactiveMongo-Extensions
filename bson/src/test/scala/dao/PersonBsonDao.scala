// Copyright (C) 2014 Fehmi Can Saglam (@fehmicans) and contributors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package reactivemongo.extensions.dao

import reactivemongo.extensions.model.Person
import reactivemongo.api.DefaultDB
import reactivemongo.extensions.dsl.BsonDsl
import scala.concurrent.{ Future, Await }
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class PersonBsonDao(_db: DefaultDB)
    extends BsonDao[Person, String](_db, "persons")
    with BsonDsl {

  def findByName(name: String): Future[Option[Person]] = {
    findOne("name" $eq name)
  }

  def dropDatabaseSync(): Boolean = {
    Await.result(_db.drop(), 20 seconds)
  }
}
