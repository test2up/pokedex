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

import redux.api.Dispatcher
import redux.api.Store
import redux.api.enhancer.Middleware
import timber.log.Timber

//class LoggingMiddleware: Middleware<ApplicationState> {
//    override fun dispatch(store: Store<ApplicationState>, action: Action, next: NextDispatcher) {
//
//    }
//}

fun createLoggerMiddleware(): Middleware<ApplicationState> {
    return Middleware { store: Store<ApplicationState>, next: Dispatcher, action: Any ->

        val actionCanonicalName = action.javaClass.simpleName
        val prevState = store.state
        next.dispatch(action)
        val curState = store.state
        Timber.d("""
                +-------------------
                |  action : $actionCanonicalName
                |  prev state: $prevState
                |  current state: $curState
                +-------------------
            """.trimIndent())

        action
    }
}