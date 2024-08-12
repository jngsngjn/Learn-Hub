import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Autoplay } from 'swiper/modules';
import 'swiper/css/bundle';
import './HomeContent.css';

const HomeContent = () => {
    return (
        <div className="home-content">
            {/* Hero Section */}
            <section className="hero-section">
                <Swiper
                    modules={[Navigation, Pagination, Autoplay]}
                    spaceBetween={50}
                    slidesPerView={1}
                    navigation
                    pagination={{ clickable: true }}
                    autoplay={{ delay: 5000 }}
                >
                    <SwiperSlide>
                        <div className="slide-content">
                            <h1>Advancing Robotics Research</h1>
                            <p>Pushing the boundaries of technology</p>
                        </div>
                    </SwiperSlide>
                    <SwiperSlide>
                        <div className="slide-content">
                            <h1>Innovative Solutions</h1>
                            <p>Creating the future of automation</p>
                        </div>
                    </SwiperSlide>
                </Swiper>
            </section>

            {/* About Section */}
            <section id="about" className="about-section">
                <h2>About Robotics Lab</h2>
                <p>We are dedicated to advancing the field of robotics through cutting-edge research and innovative solutions.</p>
            </section>

            {/* Services Section */}
            <section id="services" className="services-section">
                <h2>Our Services</h2>
                <div className="services-grid">
                    <div className="service-item">
                        <h3>Research & Development</h3>
                        <p>Pushing the boundaries of robotics technology.</p>
                    </div>
                    <div className="service-item">
                        <h3>Custom Solutions</h3>
                        <p>Tailored robotics solutions for your specific needs.</p>
                    </div>
                    <div className="service-item">
                        <h3>Consulting</h3>
                        <p>Expert advice on robotics implementation and strategy.</p>
                    </div>
                </div>
            </section>

            {/* Portfolio Section */}
            <section id="portfolio" className="portfolio-section">
                <h2>Our Projects</h2>
                <div className="portfolio-grid">
                    <div className="portfolio-item">
                        <img src="/path-to-image1.jpg" alt="Project 1" />
                        <h3>Autonomous Navigation System</h3>
                    </div>
                    <div className="portfolio-item">
                        <img src="/path-to-image2.jpg" alt="Project 2" />
                        <h3>Robotic Arm for Precision Manufacturing</h3>
                    </div>
                    {/* Add more portfolio items as needed */}
                </div>
            </section>

            {/* Contact Section */}
            <section id="contact" className="contact-section">
                <h2>Contact Us</h2>
                <form className="contact-form">
                    <input type="text" placeholder="Name" required />
                    <input type="email" placeholder="Email" required />
                    <textarea placeholder="Message" required></textarea>
                    <button type="submit">Send Message</button>
                </form>
            </section>
        </div>
    );
};

export default HomeContent;