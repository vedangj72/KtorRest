package com.example.database

import io.github.cdimascio.dotenv.dotenv
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import org.koin.dsl.module

val dotenv = dotenv()
val supabaseModule = module {
    single {
        val url = dotenv["SUPABASE_URL"]
        val key = dotenv["SUPABASE_KEY"]

        createSupabaseClient(
            supabaseUrl = url,
            supabaseKey = key
        ) {
            install(Postgrest)
        }
    }
}
