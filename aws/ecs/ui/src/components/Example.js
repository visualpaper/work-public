import React from 'react';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';

export default class Example extends React.Component {
  render() {
    return (
      <Form>
        <FormGroup>
          <Label for="exampleEmail">Email</Label>
          <Input name="email" id="exampleEmail" />
          <Label for="exampleEmail2">Email2</Label>
          <Input name="email2" id="exampleEmail2" />
        </FormGroup>
        <Button>Submit</Button>
      </Form>
    );
  }
}