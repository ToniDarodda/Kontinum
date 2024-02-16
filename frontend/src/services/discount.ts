import { CreateDiscountType, DiscountType, PatchDiscountType } from "../interfaces";
import { Fetch } from "../utils";

class DiscountService {
    async createDiscount(dataDiscount: CreateDiscountType): Promise<DiscountType> {
        const { data: discount }: { data: DiscountType } = await Fetch.post<DiscountType>('/discount', dataDiscount);

        return discount;
    }

    async getDiscount(id: string): Promise<DiscountType> {
        const { data: discount }: { data: DiscountType } = await Fetch.get<DiscountType>(`/discount/${id}`);

        return discount;
    }

    async getAllDiscount(): Promise<DiscountType[]> {
        const { data: discount }: { data: DiscountType[] } = await Fetch.get<DiscountType[]>('/discount');

        return discount;
    }

    async patchDiscount(id: string, patchData: PatchDiscountType): Promise<number> {
        const { data: objectModified }: { data: number } = await  Fetch.patch<number>(`/discount/${id}`, patchData);

        return objectModified;
    }

    async deleteDiscount(id: string): Promise<string> {
        const { data: textInfo }: { data: string } = await Fetch.delete<string>(`/discount/${id}`);

        return textInfo;
    }
}

export const discountService: DiscountService = new DiscountService();