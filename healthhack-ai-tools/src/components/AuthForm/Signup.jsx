import { InputGroup, InputRightElement, Input, Button } from "@chakra-ui/react";
import { ViewIcon, ViewOffIcon } from "@chakra-ui/icons";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Signup = () => {
  const navigate = useNavigate()
  const isLoggedIn = localStorage.getItem("loginState")

  const [inputs, setInputs] = useState({
    fullName: "",
    username: "",
    email: "",
    password: "",
  });

  const [showPassword, setShowPassword] = useState(false);

  const handleSignup = () => {
    if (!isLoggedIn) {
      fetch("http://localhost:8080/api/auth/createAccount", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: inputs.fullName,
          password: inputs.password,
        })
      })
        .then(response => response.json())
        .then(response => {
          localStorage.setItem("loginState", true);
          localStorage.setItem("userId", response['Create Account Success']);
          navigate("/")
        })
        .catch(error => {
          console.log('good luck, here\'s the error message:', error.error);
        })
    }
  }

  return (
    <>
      <Input
        placeholder="Full Name"
        fontSize={14}
        type="text"
        size={"sm"}
        value={inputs.fullName}
        onChange={(e) => setInputs({ ...inputs, fullName: e.target.value })}
      />
      <InputGroup>
        <Input
          placeholder="Password"
          fontSize={14}
          type={showPassword ? "text" : "password"}
          value={inputs.password}
          size={"sm"}
          onChange={(e) => setInputs({ ...inputs, password: e.target.value })}
        />
        <InputRightElement h={"full"}>
          <Button variant={"ghost"} size={"sm"} onClick={() => setShowPassword(!showPassword)}>
            {showPassword ? <ViewIcon /> : < ViewOffIcon />}
          </Button>
        </InputRightElement>
      </InputGroup>

      <Button w={"full"} colourScheme="blue" size={"sm"} fontSize={14} onClick={handleSignup}>
        Sign Up
      </Button>

    </>
  );
};

export default Signup;