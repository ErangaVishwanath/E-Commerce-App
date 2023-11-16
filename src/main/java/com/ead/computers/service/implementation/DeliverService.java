package com.ead.computers.service.implementation;

import com.ead.computers.dao.request.DeliverRequest;
import com.ead.computers.entities.Deliver;
import com.ead.computers.repository.DeliverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliverService {
    private final DeliverRepository deliverRepository;

    public void addDeliver(DeliverRequest request) {
        Deliver deliver = new Deliver();
        deliver.setName(request.getName());
        deliver.setPhone(request.getPhone());
        deliverRepository.save(deliver);
    }
}
