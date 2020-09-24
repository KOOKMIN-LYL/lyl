import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

import 'style/Login.css';

const Login = () => {
    return (
        <>
            <div className="loginC">
                <h5 className="loginTitle">로그인</h5>
                <form>
                    <ul>
                        <li>
                            <label className="id">아이디</label>
                            <input></input>
                        </li>
                        <li>
                            <label className="password">비밀번호</label>
                            <input></input>
                        </li>
                    </ul>
                    <Button type="submit" variant="dark" block>로그인하기</Button>
                    <div className="links">
                        <Link to="/login" className="link">아이디찾기</Link>
                        <Link to="/" className="link linkCenter">비밀번호찾기</Link>
                        <Link to="/signup" className="link">회원가입</Link>
                    </div>
                </form>
            </div>
        </>
    );
};
export default Login;