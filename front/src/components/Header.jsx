import React from 'react';
import { Link } from 'react-router-dom'

import { Navbar, Nav } from 'react-bootstrap'

import 'style/Header.css'

const Header = () => {
    return (
        <>
            <Navbar fixed="top" variant="dark" className="header">
                <Link to="/" className="headerLink"><h1>Logo</h1></Link>
                <Nav className="mr-auto">
                    <Link to="/" className="headerLink">상의</Link>
                    <Link to="/" className="headerLink">상의</Link>
                    <Link to="/" className="headerLink">상의</Link>
                    <Link to="/" className="headerLink">상의</Link>
                    <Link to="/" className="headerLink">상의</Link>
                </Nav>
                <Nav className="myList">
                    <Link to="/login" className="headerLink">LOGIN</Link>
                    <Link to="/signup" className="headerLink">JOIN US</Link>
                    <Link to="/cart" className="headerLink">CART</Link>
                    <Link to="/" className="headerLink">MYPAGE</Link>
                </Nav>
            </Navbar>
        </>
    )
};

export default Header;