import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

import 'style/Signup.css';

const Login = () => {
    return (
        <>
            <div className="signupC">
                <h5 className="signupTitle">회원가입</h5>
                <form>
                    <table border="1">
                        <tbody>
                            <tr>
                                <th>아이디</th>
                                <td><input type="text"></input>&nbsp;(영문소문자/숫자, 4~16자)</td>
                            </tr>
                            <tr>
                                <th>비밀번호</th>
                                <td><input type="password"></input>&nbsp;(영문소문자/숫자, 4~16자)</td>
                            </tr>
                            <tr>
                                <th>비밀번호 확인</th>
                                <td><input type="password"></input>&nbsp;(영문소문자/숫자, 4~16자)</td>
                            </tr>
                            <tr>
                                <th>이름</th>
                                <td><input type="text"></input></td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td><input type="text"></input></td>
                            </tr>
                            <tr>
                                <th>휴대전화</th>
                                <td><input type="text"></input></td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td><input type="text"></input></td>
                            </tr>
                        </tbody>
                    </table>
                    <div className="signupBtn">
                        <Button variant="dark">회원가입</Button>
                    </div>
                </form>
            </div>
        </>
    );
};
export default Login;