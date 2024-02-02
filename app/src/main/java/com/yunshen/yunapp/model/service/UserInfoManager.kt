package com.yunshen.yunapp.model.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserInfoManager(private val content:Context) {
    companion object{
        private val Context.userStore :DataStore<Preferences> by preferencesDataStore("user_Store")
        val LOGIN : Preferences.Key<Boolean> = booleanPreferencesKey("LOGIN")
        val USERNAME : Preferences.Key<String> = stringPreferencesKey("USERNAME")
    }

    // val logged:Flow<Boolean> = content.userStore.data.map { it[LOGIN] ?: false }
    val userNames:Flow<String> = content.userStore.data.map { it[USERNAME] ?: "" }

    suspend fun save(userName:String){
        content.userStore.edit {
            it[LOGIN] = userName.isNotEmpty()
            it[USERNAME] = userName
        }
    }

    suspend fun logout(){
        content.userStore.edit {
            it[LOGIN] = false
            it[USERNAME] = ""
        }
    }
}