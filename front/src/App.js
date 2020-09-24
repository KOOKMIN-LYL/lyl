import React from 'react';
import { Route } from 'react-router-dom';
import { BrowserRouter } from 'react-router-dom';

import { Login, Signup, Product, Cart, Order } from 'pages';
import Header from './components/Header'
import 'style/App.css'

function App() {
  return (
    <>
      <BrowserRouter>
        <Header></Header>
        <div className="content">
          <Route exact path='/login' component={Login} />
          <Route exact path='/signup' component={Signup} />
          <Route exact path='/product' component={Product} />
          <Route exact path='/cart' component={Cart} />
          <Route exact path='/order' component={Order} />
        </div>
      </BrowserRouter>
    </>
  );
}

export default App;
