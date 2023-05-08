-- 优惠券id
local voucherId = ARGV[1]

-- 库存key
local stockKey = 'seckill:stock:' .. voucherId

-- 判断库存是否充足
if(tonumber(redis.call('get', stockKey)) <= 0) then
    -- 库存不足，返回1
    return 1
end

-- 扣减库存
redis.call('incrby', stockKey, -1)

return 0