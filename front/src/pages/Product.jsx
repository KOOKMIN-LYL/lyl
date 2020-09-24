import React from 'react';

import { Button } from 'react-bootstrap'

import 'style/Product.css'
import img from '1.jpg';

const Product = () => {
    return (
        <>
            <div className="imgArea">
                <div className="img">
                    <img src={img}></img>
                </div>
            </div>
            <div className="detailArea">
                <div className="productName">
                    <h4>CROWN OF THORNS RING</h4>
                </div>
                <div className="productDetail">
                    <table>
                        <tbody>
                            <tr>
                                <td className="price" colSpan="2">199,000 won</td>
                            </tr>
                            <tr>
                                <th>제조사</th>
                                <td>자체제작</td>
                            </tr>
                            <tr>
                                <th>원산지</th>
                                <td>국내</td>
                            </tr>
                            <tr>
                                <th>상품코드</th>
                                <td>P0000BND</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div className="productOption">
                    <table>
                        <tbody>
                            <tr>
                                <th>옵션</th>
                                <td><select>
                                    <option value="">- [필수] 옵션을 선택해 주세요 -</option>
                                    <option value="**" disabled>-------------------</option>
                                    <option value="학생">학생</option>
                                    <option value="회사원">회사원</option>
                                    <option value="기타">기타</option>
                                </select></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div className="buttonArea">
                    <Button variant="dark" className="darkBtn" block>BUY NOW</Button>
                    <Button variant="secondary" className="greyBtn">ADD TO CART</Button>
                    <Button variant="secondary" className="greyBtn">WISHLIST</Button>
                </div>
            </div>
        </>
    )
};
export default Product;