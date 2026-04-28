package com.example.database


import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.server.application.Application


fun Application.configureSupabase(): SupabaseClient {

    return createSupabaseClient(
        supabaseUrl = "https://xeqzqcupeweagcywvduo.supabase.co",
        supabaseKey = "sb_publishable_M78FHGm5Z5cXV0u9hxKphQ_aUoewKSO"
    ) {
        install(Postgrest)
    }
}