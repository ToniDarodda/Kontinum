export const BaseBusiness: string = "/business"

export const CreateBusiness: string = BaseBusiness

export const GetBusiness = (businessId: string): string => BaseBusiness + `/${businessId}`

export const PatchBusiness = (businessId: string): string => BaseBusiness + `/${businessId}`

export const DeleteBusiness = (businessId: string): string => BaseBusiness + `/${businessId}`