import {BusinessType, CreateBusinessType, LoginBusinessType, PatchBusinessType} from "../interfaces";
import { Fetch } from "../utils";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;

class BusinessService {
    async createBusiness(businessData: CreateBusinessType): Promise<BusinessType> {
        const { data: business }: { data: BusinessType } = await Fetch.post<BusinessType>('/business/register', businessData);
        return business
    }

    async loginBusiness(businessData: LoginBusinessType): Promise<string> {
        const { data: token }: { data: string } = await Fetch.post<string>('/business/login', businessData);

        return token;
    }

    async getBusiness(businessId: string): Promise<BusinessType> {
        const { data: business }: { data: BusinessType } = await Fetch.get<BusinessType>(`/business/${businessId}`)

        return business
    }

    async patchBusiness(businessData: PatchBusinessType): Promise<number> {
        const { data: objectModified }: { data: number } = await Fetch.patch<number>(`/business`, businessData)

        return objectModified
    }

    async deleteBusiness(businessId: number): Promise<string> {
        const { data: textInfo }: { data: string } = await Fetch.delete<string>(`/business/${businessId}`)
        return textInfo;
    }
}

export const businessService: BusinessService = new BusinessService();
