package com.testing.mobinttest.data.remote.response

data class Response(
    val companies: List<Company>,
    val limit: Int,
    val offset: Int
)