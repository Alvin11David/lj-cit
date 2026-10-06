/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { NewStudent } from '../models/NewStudent';
import type { StudentApiResponse } from '../models/StudentApiResponse';
import type { StudentListApiResponse } from '../models/StudentListApiResponse';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class DefaultService {
    /**
     * Creates a student
     * @param requestBody
     * @returns StudentApiResponse Student Successfully Created
     * @throws ApiError
     */
    public static createStudent(
        requestBody: NewStudent,
    ): CancelablePromise<StudentApiResponse> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/v1/students',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `Bad Request`,
                500: `Internal Server Error`,
            },
        });
    }
    /**
     * Gets all students
     * @returns StudentListApiResponse Students fetched Successfully
     * @throws ApiError
     */
    public static getAllStudents(): CancelablePromise<StudentListApiResponse> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/v1/students',
        });
    }
    /**
     * Returns a student
     * @param id
     * @returns StudentApiResponse Student Fetched Successfully
     * @throws ApiError
     */
    public static getStudentById(
        id: number,
    ): CancelablePromise<StudentApiResponse> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/v1/students/{id}',
            path: {
                'id': id,
            },
            errors: {
                404: `Student Not Found`,
            },
        });
    }
    /**
     * Edits an Existing Student
     * @param id
     * @param requestBody
     * @returns StudentApiResponse Student field(s) edited successfully
     * @throws ApiError
     */
    public static editStudent(
        id: number,
        requestBody: NewStudent,
    ): CancelablePromise<StudentApiResponse> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/api/v1/students/{id}',
            path: {
                'id': id,
            },
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                404: `Student Not Found`,
            },
        });
    }
    /**
     * Deletes a given student
     * @param id
     * @returns void
     * @throws ApiError
     */
    public static deleteStudent(
        id: number,
    ): CancelablePromise<void> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/api/v1/students/{id}',
            path: {
                'id': id,
            },
        });
    }
}
