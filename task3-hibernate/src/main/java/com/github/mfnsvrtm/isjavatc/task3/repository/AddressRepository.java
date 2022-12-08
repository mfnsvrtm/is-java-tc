package com.github.mfnsvrtm.isjavatc.task3.repository;

import com.github.mfnsvrtm.isjavatc.task3.dao.AddressDao;
import com.github.mfnsvrtm.isjavatc.task3.entity.Address;

import java.util.List;
import java.util.Optional;

public class AddressRepository extends Repository<Address> implements AddressDao {

    @Override
    public List<Address> getAll() {
        return getAll(Address.class);
    }

    @Override
    public Optional<Address> getById(int id) {
        return getById(id, Address.class);
    }

}
