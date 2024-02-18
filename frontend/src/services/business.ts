import {BusinessType, CreateBusinessType, LoginBusinessType, PatchBusinessType, UserType} from "../interfaces";
import { Fetch } from "../utils";

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

    async getBusinessUser(): Promise<UserType[]> {
        const { data: users }: { data: UserType[]} = await Fetch.get<UserType[]>(`/business/users`);

        return users;
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
