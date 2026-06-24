package com.bfhl.service;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;

public interface BfhlService {

    /**
     * Processes the input data array and returns classified results.
     *
     * @param request the incoming request containing the data array
     * @return BfhlResponse with classified numbers, alphabets, special characters, sum, and concat string
     */
    BfhlResponse processData(BfhlRequest request);
}
