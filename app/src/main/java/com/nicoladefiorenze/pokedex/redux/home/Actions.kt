/*
 * Copyright 2017 Nicola De Fiorenze
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.nicoladefiorenze.pokedex.redux.home

import com.brianegan.bansa.Action
import com.nicoladefiorenze.pokedex.PokemonsQuery
import com.nicoladefiorenze.pokedex.redux.ApplicationState

data class INIT(val initialState: ApplicationState = ApplicationState()) : Action
object POKEMONS_FETCH : Action
object POKEMONS_FETCHING : Action
data class POKEMONS_FETCHED_FULLFILED(val data: MutableList<PokemonsQuery.Pokemon>?) : Action
object POKEMONS_FETCHED_ERROR : Action
