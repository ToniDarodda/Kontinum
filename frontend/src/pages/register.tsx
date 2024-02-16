import { useForm, SubmitHandler } from "react-hook-form"

type Inputs = {
    email: string
    password: string
}

interface RegisterInterface {

}

// eslint-disable-next-line no-empty-pattern
export function Register({}: RegisterInterface) {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<Inputs>()

    const onSubmit: SubmitHandler<Inputs> = async (data) => {

    }

    return (
        <>
            <form onSubmit={handleSubmit(onSubmit)}>
                <input defaultValue="test" {...register("email")} />
                <input {...register("password", { required: true })} />
                {errors.password && <span>This field is required</span>}
                <input type="submit" />
            </form>
        </>
    )
}