import React from "react";
import ReactDOM from "react-dom";
import Button from "../Button";
import { render, cleanup } from '@testing-library/react';
import renderer from 'react-test-renderer';
import '@testing-library/jest-dom';


afterEach(cleanup)
it(" renders no crashing", () => {
    const div = document.createElement("div");
    ReactDOM.render(<Button></Button>, div)
})

it(" render button correctly", () => {
    const { getByTestId } = render(<Button text = "Close"></Button>)
    expect(getByTestId('button')).toHaveTextContent("Close");
})

it("matches snapshot", () => {
    const tree = renderer.create(<Button text = "Close"></Button>).toJSON();
    expect(tree).toMatchSnapshot();
})