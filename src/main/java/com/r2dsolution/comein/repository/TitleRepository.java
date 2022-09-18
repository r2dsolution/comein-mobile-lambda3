package com.r2dsolution.comein.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.r2dsolution.comein.entity.TitleM;

public interface TitleRepository extends CrudRepository<TitleM, String> {

	public Optional<TitleM> findByNameTh(String name);
	public Optional<TitleM> findByNameEn(String name);
}