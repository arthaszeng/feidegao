package com.darkhorse.feidegao.infrastructure.repositoryimpl;

import com.darkhorse.feidegao.domainmodel.Proposal;
import com.darkhorse.feidegao.domainservice.repository.ProposalRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProposalRepositoryImpl implements ProposalRepository {
    @Override
    public Proposal getProposalById(String id) {
        return null;
    }
}
