package com.leondev7.marveltest.core.network.mock

import com.leondev7.marveltest.core.network.responses.BaseResponse
import com.leondev7.marveltest.core.network.responses.CharacterResponse
import com.leondev7.marveltest.core.network.responses.CharacterThumbnailResponse
import com.leondev7.marveltest.core.network.responses.DataResponse

object MockResponse {

    val characterResponse: BaseResponse<CharacterResponse> =
        BaseResponse(
            code = 200,
            status = "",
            message = "",
            data = DataResponse(
                offset = 0,
                limit = 0,
                total = 1,
                count = 1,
                results = arrayOf(
                    CharacterResponse(
                        id = 0L,
                        name = "",
                        description = "",
                        CharacterThumbnailResponse(
                            path = "",
                            extension = "jpg"
                        ),
                    )
                )
            )
        )
}