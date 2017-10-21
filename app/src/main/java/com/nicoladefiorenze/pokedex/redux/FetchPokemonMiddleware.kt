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
package com.nicoladefiorenze.pokedex.redux

import com.apollographql.apollo.api.Response
import com.brianegan.bansa.Action
import com.brianegan.bansa.Middleware
import com.brianegan.bansa.NextDispatcher
import com.nicoladefiorenze.pokedex.PokemonsQuery
import com.nicoladefiorenze.pokedex.inject.DaggerPokemonProviderComponent
import com.nicoladefiorenze.pokedex.redux.home.POKEMONS_FETCH
import com.nicoladefiorenze.pokedex.redux.home.POKEMONS_FETCHED_ERROR
import com.nicoladefiorenze.pokedex.redux.home.POKEMONS_FETCHED_FULLFILED
import com.nicoladefiorenze.pokedex.redux.home.POKEMONS_FETCHING
import com.nicoladefiorenze.pokedex.remote.PokemonRemoteProvider
import timber.log.Timber
import javax.inject.Inject

class FetchPokemonMiddleware : Middleware<ApplicationState> {

    init {
        DaggerPokemonProviderComponent.create().inject(this)
    }

    @Inject lateinit var pokemonProvider: PokemonRemoteProvider

    override fun dispatch(store: com.brianegan.bansa.Store<ApplicationState>, action: Action, next: NextDispatcher) {
        when (action) {
            is POKEMONS_FETCH -> {
                val currentTimeMillis = System.currentTimeMillis()
                next.dispatch(POKEMONS_FETCHING)
                pokemonProvider.getPokemons()
                        .subscribe({ response: Response<PokemonsQuery.Data>? ->
                            Timber.d("Pokemons fetched $response")
                            next.dispatch(POKEMONS_FETCHED_FULLFILED(response?.data()?.pokemons()))
                            Timber.d("Pokemons fetched in : ${System.currentTimeMillis()-currentTimeMillis} millis")
                        }, {
                            Timber.e(it)
                            next.dispatch(POKEMONS_FETCHED_ERROR)
                        })
            }
            else -> next.dispatch(action)
        }

    }
}