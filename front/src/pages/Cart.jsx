import React from 'react';
import { Button } from 'react-bootstrap';

import 'style/Cart.css';
import img from '1.jpg';

const Cart = () => {
    return (
        <>
            <div className="cartC">
                <h5 className="cartTitle">장바구니</h5>
                <div className="cartProducts">
                    <table>
                        <thead>
                            <tr>
                                <th><input type="checkbox"></input></th>
                                <th>이미지</th>
                                <th>싱품정보</th>
                                <th>판매가</th>
                                <th>수량</th>
                                <th>선택</th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><input type="checkbox"></input></td>
                                <td> <img src={img} width="90"></img></td>
                                <td>
                                    SACRED HEART PENDANT
                                    <div>[옵션: 전체 실버/marina 2.4mm 45cm (+30,000)] </div>
                                </td>
                                <td>159,000 won</td>
                                <td><input value="1"></input><button>변경</button></td>
                                <td className="buttons">
                                    <Button variant="dark" size="sm">주문하기</Button>
                                    <Button variant="light" size="sm">관심상품등록</Button>
                                    <Button variant="light" size="sm">삭제</Button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div className="totalPrice">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>결제예정금액</th>
                                </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>105,000 won</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div className="orderBtn">
                    <Button variant="dark">전체상품주문</Button>
                    <Button variant="secondary">선택상품주문</Button>
                    <Button variant="light">쇼핑계속하기</Button>
                </div>
            </div>
        </>
    );
};
export default Cart;