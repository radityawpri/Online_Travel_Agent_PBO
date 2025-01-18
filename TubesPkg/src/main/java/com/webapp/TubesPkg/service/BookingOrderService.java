package com.webapp.TubesPkg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.TubesPkg.models.BookingOrder;
import com.webapp.TubesPkg.models.PackageTravel;
import com.webapp.TubesPkg.repository.BookingOrderRepository;

@Service
public class BookingOrderService {
    
    @Autowired
    private BookingOrderRepository bookingOrderRepository;

    public List<BookingOrder> getAllBookingOrders(){
        return bookingOrderRepository.findAll();
    }

    public Optional<BookingOrder> getBookingById(int id){
        return bookingOrderRepository.findById(id);
    }

    public BookingOrder createBookingOrder(PackageTravel packageTravel, String userName, String userEmail, String noTelp, String paymentMethod){
        BookingOrder bookingOrder = new BookingOrder();
        bookingOrder.setPackageTravel(packageTravel);
        bookingOrder.setUserName(userName);
        bookingOrder.setUserEmail(userEmail);
        bookingOrder.setNoTelp(noTelp);
        bookingOrder.setPaymentMethod(paymentMethod);
        bookingOrder.setTotalPrice(packageTravel.getTotalPrice());
        return bookingOrderRepository.save(bookingOrder);
    }

    public BookingOrder updatBookingOrder(int id, BookingOrder bookingOrder){
        if (bookingOrderRepository.existsById(id)){
            bookingOrder.setId(id);
            return bookingOrderRepository.save(bookingOrder);
        }
        return null;
    }
}
