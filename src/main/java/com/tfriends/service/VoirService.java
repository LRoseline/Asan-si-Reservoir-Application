package com.tfriends.service;

import java.util.List;

import com.tfriends.domain.VoirAPI;
import com.tfriends.domain.Voirs;
import com.tfriends.mapper.VoirMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoirService {
    
    @Autowired
    private VoirMapper m;

    public List<Voirs> VoirLists (String jurisdiction) {
        return m.vlist(jurisdiction);
    }

    public VoirAPI VoirClick (int no) {
        return m.vinfo(no);
    }
}
