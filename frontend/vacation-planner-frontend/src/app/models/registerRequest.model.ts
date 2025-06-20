export interface RegisterRequest {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    dateOfBirth: string; // format: "yyy-MM-dd"
}