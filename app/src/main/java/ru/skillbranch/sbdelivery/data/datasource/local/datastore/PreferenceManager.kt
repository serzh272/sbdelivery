package ru.skillbranch.sbdelivery.data.datasource.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.skillbranch.sbdelivery.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    PreferenceManager.SB_DELIVERY_PREFERENCES_NAME
)

@Singleton
class PreferenceManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    companion object {
        const val SB_DELIVERY_PREFERENCES_NAME = BuildConfig.APPLICATION_ID + "prefs"
        val accessTokenKey = stringPreferencesKey("accessToken")
        val refreshTokenKey = stringPreferencesKey("refreshToken")
    }

    private val dataStore
        get() = context.dataStore

    val accessTokenFlow: Flow<String?> = dataStore.data.map { prefs ->
        prefs[accessTokenKey]
    }

    val refreshTokenFlow: Flow<String?> = dataStore.data.map { prefs ->
        prefs[refreshTokenKey]
    }

    suspend fun updateAccessToken(accessToken: String?) {
        dataStore.edit {
            if (accessToken != null) {
                it[accessTokenKey] = accessToken
            } else {
                it.remove(accessTokenKey)
            }
        }
    }

    suspend fun updateRefreshToken(refreshToken: String?) {
        dataStore.edit {
            if (refreshToken != null) {
                it[refreshTokenKey] = refreshToken
            } else {
                it.remove(refreshTokenKey)
            }
        }
    }

    suspend fun clearTokens() {
        dataStore.edit {
            it.remove(accessTokenKey)
            it.remove(refreshTokenKey)
        }
    }
}