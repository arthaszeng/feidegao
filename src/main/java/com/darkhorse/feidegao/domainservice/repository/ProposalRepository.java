package com.darkhorse.feidegao.domainservice.repository;

import com.darkhorse.feidegao.domainmodel.Proposal;

public interface ProposalRepository {
    Proposal getProposalById(String id);
}
