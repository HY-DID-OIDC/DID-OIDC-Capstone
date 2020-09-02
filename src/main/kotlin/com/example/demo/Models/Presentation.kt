package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonProperty

data class Presentation(
    // 아직 RequestProof에 대한 선언이 이뤄지지 않았음    
    @JsonProperty("requested_proof") var requestedProof: RequestedProof
)
