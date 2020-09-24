import React from 'react';
import { Button } from 'react-bootstrap';

import 'style/Order.css';
import img from '1.jpg';

const Order = () => {
    return (
        <>
            <div className="orderC">
                <h5 className="orderTitle">주문서 작성</h5>
                <div className="orderProducts">
                    <table>
                        <thead>
                            <tr>
                                <th>이미지</th>
                                <th>싱품정보</th>
                                <th>판매가</th>
                                <th>수량</th>
                                <th>합계</th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td> <img src={img} width="90"></img></td>
                                <td>
                                    SACRED HEART PENDANT
                                    <div>[옵션: 전체 실버/marina 2.4mm 45cm (+30,000)] </div>
                                </td>
                                <td>159,000 won</td>
                                <td>1</td>
                                <td>159,000 won</td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div className="orderInfo">
                    주문 정보
                    <table border="1">
                        <tbody>
                            <tr>
                                <th>주문하시는 분</th>
                                <td><input type="text"></input></td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td><input type="password"></input></td>
                            </tr>
                            <tr>
                                <th>휴대전화</th>
                                <td><input type="password"></input></td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td><input type="text"></input></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div className="orderInfo">
                    배송 정보
                    <table border="1">
                        <tbody>
                            <tr>
                                <th>주문하시는 분</th>
                                <td><input type="text"></input></td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td><input type="password"></input></td>
                            </tr>
                            <tr>
                                <th>휴대전화</th>
                                <td><input type="password"></input></td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td><input type="text"></input></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div className="payment">
                    <Button variant="dark">결제하기</Button>
                </div>
            </div>
        </>
    );
};
export default Order;