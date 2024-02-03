import { Button, Input } from "@chakra-ui/react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();
  const isLoggedIn = localStorage.getItem("loginState");
  const [inputs, setInputs] = useState({
    email: "",
    password: "",
  });

  const handleLogin = () => {
    if (!isLoggedIn) {
      localStorage.setItem("loginState", true);
      navigate("/")
    }
  }

  return (
    <>
      <Input
        placeholder="Email"
        fontSize={14}
        type="email"
        size={"sm"}
        value={inputs.email}
        onChange={(e) => setInputs({ ...inputs, email: e.target.value })}
      />
      <Input
        placeholder="Password"
        fontSize={14}
        type="password"
        size={"sm"}
        value={inputs.password}
        onChange={(e) => setInputs({ ...inputs, password: e.target.value })}
      />

      <Button w={"full"} colourScheme="blue" size={"sm"} fontSize={14} onClick={handleLogin}>
        Log in
      </Button>
    </>
  );
};

export default Login;
