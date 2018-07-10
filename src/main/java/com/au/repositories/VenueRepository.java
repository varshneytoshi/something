package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.au.entities.Venue;
@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {
	@Query("select v from Venue v where v.venueLocation=:location and v.venuePrice<=:price and v.venueCapacity>=:noOfGuests")
	List<Venue> findbyPriceAndLocation(@Param("price") double priceBound,@Param("location") String location,@Param("noOfGuests") int noOfGuests);
	

}
