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
package com.nicoladefiorenze.pokedex.home

import android.os.Bundle
import com.nicoladefiorenze.pokedex.PokeActivity
import com.nicoladefiorenze.pokedex.R
import com.nicoladefiorenze.pokedex.redux.ApplicationState
import com.nicoladefiorenze.pokedex.redux.home.POKEMONS_FETCH
import redux.api.Store
import javax.inject.Inject

class HomeActivity : PokeActivity() {

    @Inject lateinit var store: Store<ApplicationState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.home_content, HomeFragment())
                .commit()
    }

    override fun onStart() {
        super.onStart()
        store.dispatch(POKEMONS_FETCH)
    }
}
